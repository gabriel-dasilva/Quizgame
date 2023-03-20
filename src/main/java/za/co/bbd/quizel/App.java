package za.co.bbd.quizel;

import za.co.bbd.quizel.utils.JsonDataMapper;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<Genre> data = JsonDataMapper.getAllData();
        System.out.println(data);
    }
}
