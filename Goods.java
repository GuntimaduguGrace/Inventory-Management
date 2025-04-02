package models;
public class Goods {
private int id;
private String name;
private String desc;
private int price;
public Goods(int id, String name, String desc, int price) {
this.id =id;
this.name=name;
this.desc=desc;
this.price=price;
}
public int getId() {return id;}
public int getPrice() {return price;}
public String getName() {return name;}
public String getDesc() { return desc;}

}
