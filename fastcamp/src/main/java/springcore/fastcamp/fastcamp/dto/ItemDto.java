package springcore.fastcamp.fastcamp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor
@Getter
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    public ItemDto(JSONObject itemJSON) {
        this.title = itemJSON.getString("title");
        this.link = itemJSON.getString("link");
        this.image = itemJSON.getString("image");
        this.lprice = itemJSON.getInt("lprice");
    }
}
