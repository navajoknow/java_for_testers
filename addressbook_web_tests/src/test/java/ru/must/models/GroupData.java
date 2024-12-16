package ru.must.models;

public record GroupData (String id, String name, String header, String footer) {

    public GroupData() {
        this("", "", "", "");
    }

    // класс record не позволяет менять поля созданных объектов (они final), поэтому мы используются методы, возвращающие новые объекты
    // с отдельными полями, при этом остальные поля остаются неизменными: берутся из текущего (копируемого) объекта
    public GroupData withId (String id){
        return new GroupData(id, this.name, this.header, this.footer);
    }

    public GroupData withName (String name){
        return new GroupData(this.id, name, this.header, this.footer);
    }

    public GroupData withHeader (String header){
        return new GroupData(this.id, this.name, header, this.footer);
    }

    public GroupData withFooter (String footer){
        return new GroupData(this.id, this.name, this.header, footer);
    }

}