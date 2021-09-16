package com.inflearn.lecture.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Category root;

  @NotBlank
  private String name;

  @OneToMany
  private List<Category> subCategories = new ArrayList<>();

  public Category(String name) {
    this(null, name);
  }

  public Category(Category root, String name) {
    verify(name);
    this.root = root;
    this.name = name;
    // TODO: 2021/09/13 메서드명 누군가 맛깔나게 바꿔줘~
    if(Objects.nonNull(root)) {
      root.addSubCategory(this);
    }
  }

  private void verify(String name) {
    if(!StringUtils.hasText(name)) {
      throw new IllegalArgumentException("블랭크 쓰지마!");
    }
  }

  public List<Category> getSubCategory() {
    if (!isRootCategory()) {
      throw new IllegalStateException("루트아니면 접근하지마!");
    }
    return subCategories;
  }

  private boolean isRootCategory() {
    return Objects.isNull(root);
  }

  private void addSubCategory(Category subCategory) {
    this.subCategories.add(subCategory);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return Objects.equals(id, category.id) || Objects.equals(root, category.root) && Objects.equals(name, category.name) && Objects.equals(subCategories, category.subCategories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, root, name, subCategories);
  }
}
