package org.converter.service.excel;

import org.converter.model.NumberModel;
import java.util.List;

public interface ExcelService {

    public void exportExcel(List<NumberModel> numberModels);

}
