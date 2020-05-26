package com.openllhs.file;

import lombok.Data;

import java.io.Serializable;

@Data
public class FastDFSFile implements Serializable {

    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名 jpg png gif
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建者
    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
