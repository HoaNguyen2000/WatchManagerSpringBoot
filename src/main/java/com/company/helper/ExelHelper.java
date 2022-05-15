package com.company.helper;

import com.company.entity.Brands;
import com.company.entity.Product;
import com.company.services.BrandsService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Component
public class ExelHelper {
    private final BrandsService brandsService;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id",
            "Tên sản phẩm",
            "Tên thương hiệu",
            "Link ảnh",
            "Giá cũ",
            "Giá mới",
            "Đường dẫn",
            "Loại",
            "Mô tả",
            "Ngày tạo",
            "Ngày cập nhật"};
    static String SHEET = "SanPham";

    public ExelHelper(BrandsService brandsService) {
        this.brandsService = brandsService;
    }


    public ByteArrayInputStream productsToExcel(List<Product> products) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);
            // Header
            Row headerRow = sheet.createRow(0);
            int length = HEADERs.length;
            for (int col = 0; col < length; col++) {

                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            Locale locale = new Locale("vi", "VN");
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getBrands().getName());
                row.createCell(3).setCellValue(product.getImageLink());
                row.createCell(4).setCellValue(numberFormat.format(product.getOldPrice()));
                row.createCell(5).setCellValue(numberFormat.format(product.getPrice()));
                row.createCell(6).setCellValue(product.getSlug());
                row.createCell(7).setCellValue(product.getType());
                row.createCell(8).setCellValue(product.getDescription());
                row.createCell(9).setCellValue(simpleDateFormat.format(product.getCreateTime()));
                row.createCell(10).setCellValue(simpleDateFormat.format(product.getUpdatedTime()));
            }
            for (int i = 0; i < length; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }

    public List<Product> excelToProducts(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                var product = new Product();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            product.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            Long brandId = (long)currentCell.getNumericCellValue();
                            var brand = brandsService.findById(brandId);
                            product.setBrands(brand);
                            break;
                        case 2:
                            product.setImageLink(currentCell.getStringCellValue());
                            break;
                        case 3:
                            product.setOldPrice(currentCell.getNumericCellValue());
                            break;
                        case 4:
                            product.setPrice(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            product.setSlug(currentCell.getStringCellValue());
                            break;
                        case 6:
                            product.setType(currentCell.getStringCellValue());
                            break;
                        case 7:
                            product.setDescription(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                products.add(product);
            }

            workbook.close();

            return products;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }

    public boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
}
