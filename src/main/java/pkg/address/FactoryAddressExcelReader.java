package pkg.address;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@Component
public class FactoryAddressExcelReader {

    public ArrayList<Factory> readExcel() {

        ArrayList<Factory> list = new ArrayList<>();

        try{
            String relativePath = "Factory_Address.xlsx";
            String filePath = System.getProperty("user.dir") + File.separator + relativePath;

            File file = new File(filePath);

            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //엑셀 index는 0부터 시작
            int rowindex=0;
            //시트 수
            XSSFSheet sheet = workbook.getSheetAt(0);
            //행의 수
            int rows = sheet.getPhysicalNumberOfRows();

            for(rowindex=1; rowindex <rows ; rowindex++) {

                //행 읽기
                XSSFRow row = sheet.getRow(rowindex);

                String FactoryName = String.valueOf(row.getCell(0));
                String FactoryAddress = String.valueOf(row.getCell(1));

                Factory factory = new Factory(FactoryName, FactoryAddress);
                list.add(factory);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}