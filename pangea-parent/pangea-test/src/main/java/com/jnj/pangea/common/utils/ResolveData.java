package com.jnj.pangea.common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResolveData {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public ResolveData() {
    }

    public static List<ExcelData> readExcel(String excelPath) {
        Workbook wb = null;
        Object ret = new ArrayList();

        try {
            File file = new File(excelPath);
            checkExcelVaild(file);
            String path = file.getParent();
            wb = judgeExcel(file);
            ret = solveExcel(wb);
        } catch (Exception var13) {
            System.err.println(var13.getMessage());
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

        return (List) ret;
    }

    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("not exist");
        } else if (!file.isFile() || !file.getName().endsWith("xls") && !file.getName().endsWith("xlsx")) {
            throw new Exception("not Excel");
        }
    }

    public static Workbook judgeExcel(File file) throws Exception {
        Workbook wb = null;
        InputStream in = new FileInputStream(file);
        if (file.getName().endsWith("xls")) {
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith("xlsx")) {
            wb = new XSSFWorkbook(in);
        }

        return (Workbook) wb;
    }

    public static List<ExcelData> solveExcel(Workbook wb) {
        int sheetCount = wb.getNumberOfSheets();
        List<String> regions = new ArrayList();
        List<String> values = null;
        List<ExcelData> ret = new ArrayList();

        label102:
        for (int i = 0; i < sheetCount; ++i) {
            Sheet sheet = wb.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            ExcelData excelData = new ExcelData();
            excelData.setSheetName(sheetName);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            DecimalFormat df = new DecimalFormat("0");
            int column = 0;
            int rowNo = 0;
            int lastRow = sheet.getLastRowNum();
            List<RegionData> regionList = new ArrayList();
            RegionData regionData = new RegionData();
            List<List<String>> contents = new ArrayList();
            List<String> keyField = new ArrayList();
            List<Integer> keyIndex = new ArrayList();
            List<String> fields1 = new ArrayList();
            Iterator var21 = sheet.iterator();

            while (true) {
                while (true) {
                    while (var21.hasNext()) {
                        Row row = (Row) var21.next();
                        int tmp = row.getRowNum();
                        if (row.getCell(0) != null && !row.getCell(0).toString().equals("")) {
                            if (row.getCell(0).toString().equals("regionFullPath")) {
                                rowNo = 0;
                                regions.add(row.getCell(1).toString().trim());
                                regionData.setPath(row.getCell(1).toString().trim());
                            } else {
                                Iterator var23;
                                Cell cell;
                                if (row.getCell(0).toString().equals("fieldName")) {
                                    var23 = row.iterator();

                                    while (var23.hasNext()) {
                                        cell = (Cell) var23.next();
                                        if (!cell.toString().equals("fieldName")) {
                                            if (cell.toString().equals("")) {
                                                break;
                                            }

                                            fields1.add(cell.toString().trim());
                                        }
                                    }

                                    column = fields1.size();
                                } else if (row.getCell(0).toString().equals("KEY")) {
                                    var23 = row.iterator();

                                    while (var23.hasNext()) {
                                        cell = (Cell) var23.next();
                                        if (cell.toString().equals("○")) {
                                            keyField.add(fields1.get(cell.getColumnIndex() - 1));
                                            keyIndex.add(cell.getColumnIndex() - 1);
                                        }

                                        if (cell.getColumnIndex() > column) {
                                            break;
                                        }
                                    }
                                } else {
                                    values = new ArrayList();
                                    var23 = row.iterator();

                                    while (var23.hasNext()) {
                                        cell = (Cell) var23.next();
                                        if (cell.getColumnIndex() > column) {
                                            break;
                                        }

                                        int cellType = cell.getCellType();
                                        String cellValue = "";
                                        switch (cellType) {
                                            case 0:
                                                if (DateUtil.isCellDateFormatted(cell)) {
                                                    cellValue = fmt.format(cell.getDateCellValue());
                                                } else {
                                                    cellValue = df.format(cell.getNumericCellValue());
                                                }

                                                values.add(cellValue);
                                                break;
                                            default:
                                                values.add(checkData(cell.toString()));
                                        }
                                    }

                                    contents.add(values);
                                    if (row.getRowNum() == lastRow) {
                                        regionData.setKeyField(keyField);
                                        regionData.setKeyIndex(keyIndex);
                                        regionData.setValues(contents);
                                        regionData.setFields(fields1);
                                        regionList.add(regionData);
                                        column = 0;
                                        regionData = new RegionData();
                                        contents = new ArrayList();
                                        keyField = new ArrayList();
                                        keyIndex = new ArrayList();
                                        fields1 = new ArrayList();
                                    }
                                }
                            }
                        } else {
                            if (rowNo == 0) {
                                regionData.setKeyField(keyField);
                                regionData.setKeyIndex(keyIndex);
                                regionData.setValues(contents);
                                regionData.setFields(fields1);
                                regionList.add(regionData);
                                column = 0;
                                regionData = new RegionData();
                                contents = new ArrayList();
                                keyField = new ArrayList();
                                keyIndex = new ArrayList();
                                fields1 = new ArrayList();
                            }

                            ++rowNo;
                        }
                    }

                    excelData.setRegionList(regionList);
                    ret.add(excelData);
                    continue label102;
                }
            }
        }

        return ret;
    }

    public static void write(ExcelData param, String path) {
        StringBuilder text = new StringBuilder();
        StringBuilder del = new StringBuilder();
        StringBuilder add = new StringBuilder();
        new StringBuilder();
        boolean eptFlg = param.getSheetName().indexOf("expect") != -1;
        List<RegionData> regions = param.getRegionList();
        Iterator var8 = regions.iterator();

        while (var8.hasNext()) {
            RegionData region = (RegionData) var8.next();
            StringBuilder keyStr = solveKeys(region.getKeyField());
            del.append("And I delete data from \"" + region.getPath() + "\" by keyFields \"" + keyStr + "\"\n");
            del.append("\n");
            if (eptFlg) {
                add.append("Then I check region data \"" + region.getPath() + "\" by keyFields \"" + keyStr + "\"\n");
                add.append("\n");
            } else {
                add.append("And I import \"" + region.getPath() + "\" by keyFields \"" + keyStr + "\"\n");
                add.append("\n");
            }

            Iterator var11 = region.getKeyField().iterator();

            String str;
            while (var11.hasNext()) {
                str = (String) var11.next();
                del.append("|" + str);
            }

            var11 = region.getFields().iterator();

            while (var11.hasNext()) {
                str = (String) var11.next();
                add.append("|" + str);
            }

            del.append("|\n");
            add.append("|\n");
            List<List<String>> values = region.getValues();

            for (int i = 0; i < values.size(); ++i) {
                Iterator var13 = region.getKeyIndex().iterator();

                while (var13.hasNext()) {
                    Integer index = (Integer) var13.next();
                    del.append("|" + (String) ((List) values.get(i)).get(index.intValue() + 1));
                }

                del.append("|\n");

                for (int j = 1; j < ((List) values.get(i)).size(); ++j) {
                    add.append("|" + (String) ((List) values.get(i)).get(j));
                }

                add.append("|\n");
            }

            del.append("\nAnd I wait \"" + region.getPath() + "\" Async Queue complete\n#And wait 5000 millisecond\n\n");
            add.append("\nAnd I wait \"" + region.getPath() + "\" Async Queue complete\n#And wait 5000 millisecond\n\n");
        }

        System.err.println(del);
        System.err.println(add);
        File file = new File(path, param.getSheetName() + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var18) {
                ;
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
            text.append(del + "\n\n" + add);
            writer.append(text);
            writer.close();
            out.close();
        } catch (FileNotFoundException var15) {
            ;
        } catch (UnsupportedEncodingException var16) {
            ;
        } catch (IOException var17) {
            ;
        }

        System.err.println(text);
    }

    public static StringBuilder solveKeys(List<String> param) {
        StringBuilder ret = new StringBuilder();

        String str;
        for (Iterator var2 = param.iterator(); var2.hasNext(); ret = ret.append(str + ",")) {
            str = (String) var2.next();
        }

        ret.deleteCharAt(ret.length() - 1);
        return ret;
    }

    public static String checkData(String param) {
        return param.replace("|", "\\|");
    }
}
