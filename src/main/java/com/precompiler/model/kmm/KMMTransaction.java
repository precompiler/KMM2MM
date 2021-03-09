package com.precompiler.model.kmm;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard Li
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KMMTransaction {
    @CsvBindByPosition(position = 0)
    private String date;
    @CsvBindByPosition(position = 1)
    private String payee;
    @CsvBindByPosition(position = 2)
    private String amount;
    @CsvBindByPosition(position = 3)
    private String accountOrCategory;
    @CsvBindByPosition(position = 4)
    private String memo;
    @CsvBindByPosition(position = 5)
    private String status;
    @CsvBindByPosition(position = 6)
    private String number;
}
