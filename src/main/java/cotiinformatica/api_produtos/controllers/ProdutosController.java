package cotiinformatica.api_produtos.controllers;

import cotiinformatica.api_produtos.dtos.*;
import cotiinformatica.api_produtos.entities.Produto;
import cotiinformatica.api_produtos.repositories.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {

    @PostMapping
    public ResponseEntity<ProdutoPostResponse> post(@RequestBody ProdutoPostRequest request) {
        try {
            //Criando um objeto da classe de entidade 'Produto'
            var produto = new Produto();

            produto.setId(UUID.randomUUID()); //Gerando um ID para o produto
            produto.setNome(request.nome()); //Capturando o nome enviado no request
            produto.setPreco(request.preco()); //Capturando o preço enviado no request
            produto.setQuantidade(request.quantidade()); //Capturando a quantidade enviada no request
            produto.setDataHoraCadastro(LocalDateTime.now()); //Gerando a data e hora atual
            produto.setAtivo(true); //Definindo o produto como ativo (true)

            //Criando um objeto da classe repositório de produto
            var produtoRepository = new ProdutoRepository();
            produtoRepository.insert(produto); //Salvando no banco de dados

            //Retorno
            var response = new ProdutoPostResponse(
                    201,
                    "Produto cadastrado com sucesso",
                    LocalDateTime.now(),
                    produto.getId()
            );

            //Retornar a resposta e finalizar o serviço
            return ResponseEntity.status(201).body(response);
        }
        catch(Exception e) {
            //Retorno
            var response = new ProdutoPostResponse(
                    500,
                    "Falha ao inserir o produto: " + e.getMessage(),
                    LocalDateTime.now(),
                    null
            );

            //Retornar a resposta e finalizar o serviço
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody ProdutoPutRequest request) {
        try {
            var produto = new Produto();

            produto.setId(request.id());
            produto.setNome(request.nome());
            produto.setPreco(request.preco());
            produto.setQuantidade(request.quantidade());

            var produtoRepository = new ProdutoRepository();
            if(produtoRepository.update(produto)) {
                var response = new ProdutoPutResponse(
                        200,
                        "Produto atualizado com sucesso",
                        LocalDateTime.now(),
                        produto.getId()
                );

                //HTTP 200 (OK)
                return ResponseEntity.status(200).body(response);
            }
            else {
                //HTTP 404 (NOT FOUND)
                return ResponseEntity.status(404).body("Produto não encontrado para edição.");
            }
        }
        catch(Exception e) {
            var response = new ProdutoPutResponse(
                    500,
                    "Falha ao atualizar produto: " + e.getMessage(),
                    LocalDateTime.now(),
                    null
            );

            //Retornar a resposta e finalizar o serviço
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            var  produtoRepository = new ProdutoRepository();

            if(produtoRepository.delete(id)) {
                var response = new ProdutoDeleteResponse(
                        200,
                        "Produto excluído com sucesso",
                        LocalDateTime.now(),
                        id
                );
                return ResponseEntity.status(200).body(response);
            }else {
                //HTTP 404 (NOT FOUND)
                return ResponseEntity.status(404).body("Produto não encontrado para exclusão.");
            }


        }
        catch(Exception e) {
            var response = new ProdutoDeleteResponse(
                    500,
                    "Falha ao excluir produto: " + e.getMessage(),
                    LocalDateTime.now(),
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            //Instanciar a classe de repositório de produtos
            var produtoRepository = new ProdutoRepository();
            //Consultar os produtos cadastrados
            var produtos = produtoRepository.findAll();

            //Retornar a lista com os produtos cadastrados.
            //HTTP 200 (OK) - Sucesso
            return ResponseEntity.status(200).body(produtos);
        }
        catch(Exception e) {
            //Retorno de erro HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Falha ao consultar: " + e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            //Instanciar a classe de repositório de produto
            var produtoRepository = new ProdutoRepository();
            //Consultar o produto através do ID
            var produto = produtoRepository.findById(id);

            //Retorno de sucesso HTTP 200 (OK)
            return ResponseEntity.status(200).body(produto);
        }
        catch(Exception e) {
            //Retorno de erro HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Falha ao consultar: " + e.getMessage());
        }
    }

}
