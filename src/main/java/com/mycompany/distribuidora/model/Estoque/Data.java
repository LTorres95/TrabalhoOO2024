package com.mycompany.distribuidora.model.Estoque;


public class Data {
    private int dia;
    private int mes;
    private int ano;

    private boolean valid(int number,int lowestLimit,int greatest)
    {
        if(number>lowestLimit & number<=greatest)
            return true;
        return false;
    }
    Data(int d,int m,int a)
    {
        if(valid(d,0,30)&valid(a,0,3000)&valid(m,0,12))
        {
            dia=d;
            m=mes;
            a=ano;
        }
            
    }
    public int getDia(){return dia;}
    public int getMes(){return mes;}
    public int getAno(){return ano;}
    public int toDays(){return dia+30*mes+360*ano;}


    public boolean compara(Data date)
    {
        return(date.getDia()==dia & date.getMes()==mes & date.getAno()==ano);
    }
}
