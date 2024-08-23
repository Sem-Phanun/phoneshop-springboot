package com.project.phone_shop.service.impl;

import com.project.phone_shop.dto.ProductImportDTO;
import com.project.phone_shop.entities.Product;
import com.project.phone_shop.entities.ProductImportHistory;
import com.project.phone_shop.exception.ApiException;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.mapper.ProductMapper;
import com.project.phone_shop.repository.ProductImportHistoryRepository;
import com.project.phone_shop.repository.ProductRepository;
import com.project.phone_shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        String name;
        name = "%s  %s"
                .formatted(product.getModel().getName(),product.getColor().getColorName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product",id));
    }


    @Override
    public void importProduct(ProductImportDTO importDTO) {
        //Update available product unit
        if (importDTO.getImportUnit() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Product unit can't be null");
        }
        Product product = getProductById(importDTO.getProductId());
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null) {
            availableUnit = product.getAvailableUnit();
        }

        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        //Save product import history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(productImportHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Price can't be less than zero");
        }
        Product product = getProductById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long productId, Integer quantity) {

    }

    @Override
    public Map<Integer, String> uploadProduct(MultipartFile file) {
        Map<Integer, String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("products");
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            while (rowIterator.hasNext()) {
                int rowNumber = 0;
                try {
                    Row row = rowIterator.next();
                    int cellIndex = 0;
                    Cell cellNo = row.getCell(cellIndex++);
                    rowNumber = (int) cellNo.getNumericCellValue();
                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();
                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();
                    Cell cellImportPrice = row.getCell(cellIndex++);
                    double importPrice = cellImportPrice.getNumericCellValue();
                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUnit.getNumericCellValue();
                    if (importUnit < 1){
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Import unit can't be less than 0");
                    }
                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdAndColorId(modelId, colorId);

                    Integer availableUnit = 0;
                    if (product.getAvailableUnit() != null) {
                        availableUnit = product.getAvailableUnit();
                    }

                    product.setAvailableUnit(availableUnit + importUnit);
                    productRepository.save(product);

                    //Save product import history
                    ProductImportHistory productImportHistory = new ProductImportHistory();
                    productImportHistory.setDateImport(importDate);
                    productImportHistory.setImportUnit(importUnit);
                    productImportHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    productImportHistory.setProduct(product);
                    productImportHistoryRepository.save(productImportHistory);
                } catch (Exception e) {
                    map.put(rowNumber, e.getMessage());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        String text = "Product not found with model id = %s and color id = %d";
        return productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(()-> new ApiException(HttpStatus.BAD_REQUEST,text.formatted(modelId,colorId)));
    }


}
