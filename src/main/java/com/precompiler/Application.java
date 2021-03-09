package com.precompiler;

import com.opencsv.bean.CsvToBeanBuilder;
import com.precompiler.model.kmm.KMMTransaction;
import com.precompiler.model.mm.MMTransaction;
import com.precompiler.utils.CategoryUtils;
import com.precompiler.utils.EncoderUtils;

import java.io.*;
import java.util.List;

/**
 * @author Richard Li
 */
public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        String header = "Date\tAccount\tMain Cat.\tSub Cat.\tContents\tAmount\tInc./Exp.\tDetails\n";
        String categoryCSVPath = "";
        String kmmTransactionCSVPath = "";
        String accountName = "";
        String outputPath = "";
        CategoryUtils.load(categoryCSVPath);
        List<KMMTransaction> kmmTransactions = new CsvToBeanBuilder<KMMTransaction>(new FileReader(kmmTransactionCSVPath)).withType(KMMTransaction.class)
                .withSkipLines(3).build().parse();

        StringBuilder buf = new StringBuilder();
        buf.append(header);
        kmmTransactions.stream().map(t -> new MMTransaction(accountName, t)).forEach(t -> {
            String tx = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", t.getDate(), t.getAccount(), t.getMainCategory(), t.getSubCategory(), t.getContents(), t.getAmount(), t.getType(), t.getDetails());
            buf.append(tx);
        });
        try (FileOutputStream fs = new FileOutputStream(new File(outputPath))) {
            fs.write(EncoderUtils.encodeUTF16LEWithBOM(buf.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
