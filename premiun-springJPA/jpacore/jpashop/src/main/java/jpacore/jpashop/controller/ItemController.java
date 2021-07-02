package jpacore.jpashop.controller;

import jpacore.jpashop.domain.Item;
import jpacore.jpashop.domain.items.Book;
import jpacore.jpashop.service.ItemSerrvice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemSerrvice itemSerrvice;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        book.setStackQuantity(bookForm.getStockQuantity());
        book.setPrice(bookForm.getPrice());

        itemSerrvice.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        model.addAttribute("items", itemSerrvice.findItems());
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long id, Model model) {
        Book item = (Book) itemSerrvice.findOne(id); // 캐스팅하는 방법은 유연하지 못한 방법

        BookForm form = new BookForm();
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
        form.setPrice(item.getPrice());
        form.setId(item.getId());
        form.setStockQuantity(item.getStackQuantity());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long id,@ModelAttribute("form") BookForm form) {
        Book book = new Book();
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        book.setPrice(form.getPrice());
        book.setId(id);
        book.setStackQuantity(form.getStockQuantity());

        itemSerrvice.saveItem(book);
        return "redirect:/items";

    }
}
