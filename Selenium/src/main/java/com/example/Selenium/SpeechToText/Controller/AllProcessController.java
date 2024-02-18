package com.example.Selenium.SpeechToText.Controller;

import com.example.Selenium.SpeechToText.Model.*;
import com.example.Selenium.SpeechToText.View.Response;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.ResponseEntity;

public class AllProcessController {

    DataStoreModel dataStoreModel;
    CSVStoreModel voiceCSVModel;

    CSVStoreModel fileNameCSVModel;

    CSVStoreModel textCSVModel;

    protected final String columnName1 = "Text";
    protected final String columnName2 = "Voice";
    protected final String columnName3 = "FileName";


    public void work() {

        textCSVModel = new CSVStoreModel();
        voiceCSVModel = new CSVStoreModel();
        fileNameCSVModel = new CSVStoreModel();
        dataStoreModel = new DataStoreModel();

        dataStoreModel.addStatus(EnumController.APPLICATION_STATUS_OK);

        GetDataCSVModel getDataCSVModel = new GetDataCSVModel(voiceCSVModel, fileNameCSVModel, textCSVModel, dataStoreModel);

        getDataCSVModel.getDataFromColumn(columnName1, columnName2, columnName3);

        getDataCSVModel.getNotificationErrorCSV(voiceCSVModel, fileNameCSVModel, dataStoreModel);

        if (dataStoreModel.getStatusEnumSet().equals(EnumController.ERROR_IN_CSV)) {
            Response response = new Response();
            response.SpeechToText(dataStoreModel.getNotification());
        }

        GetChunksToArrayList getChunksToArrayList = new GetChunksToArrayList();
        getChunksToArrayList.getChunksToArrayList(dataStoreModel.getArrayList_Char(), textCSVModel.getReadTextOfColumn(), dataStoreModel.getLimitChar());

        if (textCSVModel.getFlag() == true) {
        }
        if (textCSVModel.getFlag() == false) {
        }
    }
}

class work123 {
    public static void main(String[] args) {
        AllProcessController allProcessController = new
                AllProcessController();
        allProcessController.work();
    }
}
