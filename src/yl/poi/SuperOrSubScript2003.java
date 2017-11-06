package yl.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
 
//2003版本：
public class SuperOrSubScript2003 {
    /**
     *  判断上下标
     *  2003版
     *  cell 表示传入的单元格对象 book 表示传入的当前工作薄对象 
     **/
    public static String test(Cell cell, Workbook book){
              HSSFWorkbook workbook = null;
              HSSFFont font = null; 
              HSSFRichTextString rts = null;
              HSSFCellStyle style = null;
              int fromIndex = 0;
              int toIndex = 0;
              String value = "";
              //处理上下标
              workbook = (HSSFWorkbook)book;
              //判断当前单元格的内容是否为数字类型，如果是转换成字符串型
              if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){   
cell.setCellValue((cell.getNumericCellValue()+"").substring(0, (cell.getNumericCellValue()+"").indexOf(".")));
              }
              //获取单元格中的数据
              rts = (HSSFRichTextString) cell.getRichStringCellValue();
              //获取每个单元格数据的style属性
              style = (HSSFCellStyle) cell.getCellStyle();
              font = style.getFont(workbook);
             
              if(rts.numFormattingRuns() > 0){
                        for(int k = 0; k < rts.numFormattingRuns(); k++) {
           toIndex = rts.getIndexOfFormattingRun(k);
           String temp = rts.toString().substring(fromIndex, toIndex);
           System.out.println("\tSubstring [" + temp + "]");
                                //判断上标
           if(font.getTypeOffset() == HSSFFont.SS_SUPER){
                    temp = "<sup>" +temp+"</sup>";
                    System.out.println("\t______________发现上标");
           }
                                //判断下标
           if(font.getTypeOffset() == HSSFFont.SS_SUB){
                    temp = "<sub>" +temp+"</sub>";
                    System.out.println("\t______________发现下标");
           }
           value += temp;
           if(!value.equals("")){
                    font = workbook.getFontAt(rts.getFontOfFormattingRun(k));
           }
           fromIndex = toIndex;
                        }
                        toIndex = rts.length();
                        String temp1 = rts.toString().substring(fromIndex, toIndex);
        System.out.println("\tSubstring [" + temp1 + "]");
        if(font.getTypeOffset() == HSSFFont.SS_SUPER){
               temp1 = "<sup>" +temp1+"</sup>";
               System.out.println("\t______________发现上标");
        }
        if(font.getTypeOffset() == HSSFFont.SS_SUB){
               temp1 = "<sub>" +temp1+"</sub>";
               System.out.println("\t______________发现下标");
        }
        value += temp1;
       
        return value;
              }
              return cell.toString();
    }
}
 
 
 
//2007版本：
///**
//          *  判断上下标
//          *  2007版
//          **/
//         public static String superOrSubScript2007(Cell cell, Workbook book){
//                   XSSFWorkbook workbook = null;
//                   XSSFFont font = null; 
//                   XSSFRichTextString rts = null;
//                   XSSFCellStyle style = null;
//                   int runIndex = 0;
//                   int runLength = 0;
//                   String value = "";
//                   //处理上下标
//                   workbook = (XSSFWorkbook)book;
//                   if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
//                            cell.setCellValue((cell.getNumericCellValue()+"").substring(0, (cell.getNumericCellValue()+"").indexOf(".")));
//                   }
//                   rts = (XSSFRichTextString) cell.getRichStringCellValue();
//                   style = (XSSFCellStyle) cell.getCellStyle();
//                   font = style.getFont();
//                  
//                   if(rts.numFormattingRuns() > 1){
//                             for(int k = 0; k < rts.numFormattingRuns(); k++) {
//                                     runLength = rts.getLengthOfFormattingRun(k);
//                                     runIndex = rts.getIndexOfFormattingRun(k);
//                String temp = rts.toString().substring(runIndex, (runIndex + runLength));
//                System.out.println("\tSubstring [" + temp + "]");
//                try {
//                    font = rts.getFontOfFormattingRun(k);
//                }catch(NullPointerException npe) {
//                    font = workbook.getFontAt(XSSFFont.DEFAULT_CHARSET);
//                    font.setTypeOffset(XSSFFont.SS_NONE);
//                }
//                if(font.getTypeOffset() == XSSFFont.SS_SUPER){
//                         temp = "<sup>" +temp+"</sup>";
//                         System.out.println("\t______________发现上标");
//                }
//                if(font.getTypeOffset() == XSSFFont.SS_SUB){
//                         temp = "<sub>" +temp+"</sub>";
//                         System.out.println("\t______________发现下标");
//                }
//                value += temp;
//                             }
//             return value;
//                   }
//                   return cell.toString();
//         }
