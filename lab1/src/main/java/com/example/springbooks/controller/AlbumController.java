package com.example.springbooks.controller;
import com.example.springbooks.model.Album;
import com.example.springbooks.forms.AlbumForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequestMapping
public class AlbumController {
    private static List<Album> albums = new ArrayList<>();
    static {
                albums.add(new Album("Геометрия тьмы", "pyrokinesis"));
                albums.add(new Album("Born Pink", "BlackPink"));
    }
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called");
        return modelAndView;
    }
    @GetMapping(value = {"/allalbum"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        model.addAttribute("albums", albums);
        return modelAndView;
    }
    @GetMapping(value = {"/addalbum"})
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addnew");
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);
        return modelAndView;
    }

    @PostMapping(value = {"/addalbum"})
    public ModelAndView savePerson(Model model, //
                                   @ModelAttribute("albumform") AlbumForm albumForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String name = albumForm.getName();
        String author = albumForm.getAuthor();
        if (name != null && name.length() > 0 //
                && author != null && author.length() > 0) {
            Album newAlbum = new Album(name, author);
            albums.add(newAlbum);
            model.addAttribute("albums", albums);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addalbum");
        return modelAndView;
    }
    @GetMapping(value = {"/deletealbum"})
    public ModelAndView showdeletePersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("deletealbum");
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);
        return modelAndView;
    }
    @PostMapping(value = {"/deletealbum"})
    public ModelAndView deletePerson(Model model, //
                                   @ModelAttribute("albumform") AlbumForm albumForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String name = albumForm.getName();
        String author = albumForm.getAuthor();
        if (name != null && name.length() > 0 //
                && author != null && author.length() > 0) {
            Album newalbum = new Album(name, author);
            albums.remove(newalbum);
            model.addAttribute("albums", albums);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("deletealbum");
        return modelAndView;
    }
    @GetMapping(value = {"/editalbum"})
    public ModelAndView showeditPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("editalbum");
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);
        return modelAndView;
    }
    @PostMapping(value = {"/editalbum"})
    public ModelAndView editPerson(Model model, //
                                     @ModelAttribute("albumform") AlbumForm albumForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String name = albumForm.getName();
        String author = albumForm.getAuthor();
        String name1=albumForm.getNewName();
        String author1=albumForm.getNewAuthor();
        if (name != null && name.length() > 0 //
                && author != null && author.length() > 0&&name1 != null && name1.length() > 0 //
                && author1 != null && author1.length() > 0) {
            Album newalbum = new Album(name, author);
            Album newalbum1 = new Album(name1, author1);
            int index1= albums.indexOf(newalbum);
            albums.set(index1,newalbum1);

            model.addAttribute("albums", albums);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("editalbum");
        return modelAndView;
    }
    }

