package springcore.fastcamp.fastcamp.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springcore.fastcamp.fastcamp.api.NaverShopSearch;
import springcore.fastcamp.fastcamp.dto.ItemDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;


    @GetMapping("/api/search")
    public List<ItemDto> getItems(@RequestParam String query) {
        String resultString = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(resultString);
    }



}
