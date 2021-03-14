package com.lithium.vidum;

public class Video {
    private final String thumbnailURL;
    private final String name;
    private final String author;
    private final String videoURL;
    private final String fileName;

    public Video(String thumbnailURL, String videoURL, String fileName, String name, String author) {
        this.thumbnailURL = thumbnailURL;
        this.videoURL = videoURL;
        this.name = name;
        this.author = author;
        this.fileName = fileName;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getVideoURL() { return videoURL; }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getFileName() { return fileName; }
}
