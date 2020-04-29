package com.company.view;

import com.company.Database.models.BillEvaluation;
import com.company.Database.models.IngridientsEvaluation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

    public void writeBill (FileWriter billWriter, ArrayList<BillEvaluation> billEvaluations) {
        for (int i = 0; i < billEvaluations.size(); i++) {
            try {
                billWriter.write("\n" + billEvaluations.get(i).getId() + " ; " + billEvaluations.get(i).getUserId() +
                        " ; " + billEvaluations.get(i).getBill() +"€ ; " + billEvaluations.get(i).getTime());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            billWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeIngList (FileWriter listWriter, ArrayList<IngridientsEvaluation> ingridientsEvaluations) {
        for (int i = 0; i <ingridientsEvaluations.size() ; i++) {
            try {
                listWriter.write("\n" + ingridientsEvaluations.get(i).getName() + " ; " + ingridientsEvaluations.get(i).getCount());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            listWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
