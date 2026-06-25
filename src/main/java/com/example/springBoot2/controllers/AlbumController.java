package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Album;
import com.example.springBoot2.repositories.AlbumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;
    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
    // CEREATE  uparation [@PostMapping]
// Add multiple movies
    @PostMapping("/all")
    public List<Album> addAlbums(@RequestBody List<Album> albums) {
        return albumRepository.saveAll(albums);
    }
    // read  by all oporation
    @GetMapping("/all")
    public List<Album> getAllAlbums(){
        return albumRepository.findAll();
    }

    // read by id

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found!"));
    }

    // UPDATE oparation [@PutMapping] annotation
    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable int id, @RequestBody Album updateAlbum) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Album Not found"));

        album.setName(updateAlbum.getName());
        album.setArtist(updateAlbum.getArtist());
        album.setYear(updateAlbum.getYear());
        album.setTracks(updateAlbum.getTracks());

        return albumRepository.save(album);

    }

// DELETE OPARATATION [@DeleteMapping] method

    @DeleteMapping("/{id}")
    public String deleteAlbum(@PathVariable int id) {
        albumRepository.deleteById(id);
        return "album deleted successfully!";
    }

}


