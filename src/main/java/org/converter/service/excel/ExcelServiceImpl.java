package org.converter.service.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.converter.model.NumberModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class ExcelServiceImpl implements ExcelService {
    private static String OUTPUT = "output";

    /**
     * Export a list of number models to a csv file to be opened in excel
     * @param numberModels
     */
    @Override
    public void exportExcel(List<NumberModel> numberModels) {
        Workbook workbook = this.createWorkBook();
        this.createHeader(numberModels, workbook);
        this.createBody(numberModels, workbook);
        this.writeFile(workbook);
    }

    /**
     * Create a workbook
     * @return workbook
     */
    private Workbook createWorkBook() {
        Workbook workbook = new XSSFWorkbook();
        workbook.createSheet(OUTPUT);
        return workbook;
    }

    /**
     * Create the first line of the csv file to write a header
     * @param numberModels
     * @param workbook
     */
    private void createHeader(List<NumberModel> numberModels,Workbook workbook) {
        // Create a cell style with a green background
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Row row =  workbook.getSheet(OUTPUT).createRow(0);
        String[] headers = new String[] {"Original Number","Binary","Hexadecimal","Is Eight Bits"};
        IntStream.range(0, headers.length)  // Creates a stream of indices from 0 to headers.length - 1
                .forEach(i -> {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(cellStyle);
                    workbook.getSheet(OUTPUT).autoSizeColumn(i);
                });
    }

    /**
     * Create a separate line for each numberModel in the csv file
     * @param numberModels
     * @param workbook
     */
    private void createBody(List<NumberModel> numberModels, Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        IntStream.range(0, numberModels.size())
                .forEach(rowNum -> {
                    NumberModel numberModel = numberModels.get(rowNum);
                    Row row = workbook.getSheet(OUTPUT).createRow(rowNum + 1);
                    String[] properties = numberModel.getModelProperties();
                    IntStream.range(0, 4).forEach(i -> {
                        Cell cell = row.createCell(i);
                        cell.setCellValue(properties[i]);
                        if ((rowNum + 1) % 2 == 0) {
                            cell.setCellStyle(cellStyle);
                        }
                    });
                });
    }

    /**
     * Write the file out
     * @param workbook
     */
    private void writeFile(Workbook workbook) {
        try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
