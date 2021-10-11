package com.savijan.financemyself;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DatabaseTable(tableName = "item_user")
public class UserItemDB {

    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private long id;

    @Getter
    @Setter
    @DatabaseField
    private String index;

    @Getter
    @Setter
    @DatabaseField
    private String name;

    @Getter
    @Setter
    @DatabaseField
    private String age;

    public UserItemDB() {
    }
}
