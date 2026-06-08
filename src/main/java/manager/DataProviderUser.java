package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"flower@gmail.com", "Flower123!"});
        list.add(new Object[]{"margo@gmail.com", "Mmar123456$"});
        list.add(new Object[]{"margo@gmail.com", "Mmar123456$"});


        return list.iterator();

    }


    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/test")));
        String line = reader.readLine(); //  первая строка из файла
        while (line != null) {
            String[] all = line.split(",");
        list.add(new Object[]{new User()
                .setEmail(all[0])
                .setPassword(all[1])});
        line = reader.readLine();
        }

        return list.iterator();
    }
}
