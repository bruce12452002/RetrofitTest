package home.bruce.RetrofitTest.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animal {
//    @SerializedName("id")
    private int id;

    private String name;
}
