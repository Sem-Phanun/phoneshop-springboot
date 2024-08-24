package com.project.phone_shop.service.impl;

import com.project.phone_shop.dto.ProductReportDTO;
import com.project.phone_shop.dto.report.ExpenseReportDTO;
import com.project.phone_shop.entities.Product;
import com.project.phone_shop.entities.ProductImportHistory;
import com.project.phone_shop.entities.SaleDetail;
import com.project.phone_shop.projection.ProductSold;
import com.project.phone_shop.repository.ProductImportHistoryRepository;
import com.project.phone_shop.repository.ProductRepository;
import com.project.phone_shop.repository.SaleDetailRepository;
import com.project.phone_shop.repository.SaleRepository;
import com.project.phone_shop.service.ReportService;
import com.project.phone_shop.specification.ProductImportHistoryFilter;
import com.project.phone_shop.specification.ProductImportHistorySpecification;
import com.project.phone_shop.specification.SaleDetailFilter;
import com.project.phone_shop.specification.SaleDetailSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;


    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }

    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDTO> list = new ArrayList<>();
        SaleDetailFilter saleDetailFilter = new SaleDetailFilter();
        saleDetailFilter.setStartDate(startDate);
        saleDetailFilter.setEndDate(endDate);
        Specification<SaleDetail> specification = new SaleDetailSpecification(saleDetailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(specification);
        List<Long> productIds = saleDetails
                .stream()
                .map(saleDetail -> saleDetail
                        .getProduct()
                        .getProductId())
                .toList();
        Map<Long, Product> productMap = productRepository.findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));
        Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream().collect(Collectors.groupingBy(SaleDetail::getProduct));

        for (Map.Entry<Product, List<SaleDetail>> entry : saleDetailMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getProductId());
            List<SaleDetail> saleDetailList = entry.getValue();

            //Total Unit
            Integer quantity = saleDetailList
                    .stream()
                    .map(SaleDetail::getUnit)
                    .reduce(0, Integer::sum);
            double totalAmount = saleDetailList
                    .stream()
                    .mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue()).sum();
            //Not use this operation
            /* Double reduce = saleDetailList
                    .stream()
                    .map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
                    .reduce(0.0, Double::sum); */
            ProductReportDTO productReportDTO = new ProductReportDTO();
            productReportDTO.setProductId(product.getProductId());
            productReportDTO.setProductName(product.getProductName());
            productReportDTO.setProductUnit(quantity);
            productReportDTO.setProductAmount(BigDecimal.valueOf(totalAmount));
            list.add(productReportDTO);

        }

        return list;
    }

    @Override
    public List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
        ProductImportHistoryFilter productImportHistoryFilter = new ProductImportHistoryFilter();
        productImportHistoryFilter.setStartDate(startDate);
        productImportHistoryFilter.setEndDate(endDate);

        ProductImportHistorySpecification specification = new ProductImportHistorySpecification(productImportHistoryFilter);
        List<ProductImportHistory> importHistories = productImportHistoryRepository.findAll(specification);
        Set<Long> productIds = importHistories.stream()
                .map(history -> history.getProduct()
                        .getProductId())
                .collect(Collectors.toSet());
        List<Product> productList = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = productList.stream()
                .collect(Collectors
                        .toMap(product -> product
                                .getProductId(), product -> product));

        Map<Product, List<ProductImportHistory>> impListMap = importHistories
                .stream()
                .collect(Collectors
                        .groupingBy(ProductImportHistory::getProduct));

        var expenseReportDTOList = new ArrayList<ExpenseReportDTO>();

        for (Map.Entry<Product, List<ProductImportHistory>> entry : impListMap.entrySet()) {
            Product products = productMap.get(entry.getKey().getProductId());
            List<ProductImportHistory> entryValue = entry.getValue();
            int sumUnit = entryValue
                    .stream()
                    .mapToInt(proImport -> proImport
                            .getImportUnit())
                    .sum();
            double totalAmount = entryValue
                    .stream()
                    .mapToDouble(proImport -> proImport.getImportUnit() * proImport.getPricePerUnit().doubleValue())
                    .sum();
            var expenseReportDTO= new ExpenseReportDTO();
            expenseReportDTO.setProductId(products.getProductId());
            expenseReportDTO.setProductName(products.getProductName());
            expenseReportDTO.setTotalUnit(sumUnit);
            expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            expenseReportDTOList.add(expenseReportDTO);
        }

        return expenseReportDTOList;
    }
}
