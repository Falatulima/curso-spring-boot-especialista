package io.github.cursoSpringBoot.produtosapi.repository;

import io.github.cursoSpringBoot.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
