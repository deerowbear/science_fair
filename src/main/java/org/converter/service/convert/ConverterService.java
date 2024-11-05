package org.converter.service.convert;

import org.converter.model.NumberModel;

import java.util.List;

public interface ConverterService {

    public NumberModel setNumberModel(NumberModel numberModel);

    public Integer isValidInput(String input);

    public List<NumberModel> getRandomNumberOfModels(int range);

    public List<NumberModel> getNumberOfModels(int range);

}
