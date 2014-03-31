/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.email.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author null
 */
@Entity
@Table(name = "template")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Template.findAll", query = "SELECT t FROM Template t"),
    @NamedQuery(name = "Template.findById", query = "SELECT t FROM Template t WHERE t.id = :id"),
    @NamedQuery(name = "Template.findByName", query = "SELECT t FROM Template t WHERE t.name = :name")})

public class Template implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "content")
    private String content;
   

    public Template() {
    }

    public Template(Integer id) {
        this.id = id;
    }

    public Template(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
