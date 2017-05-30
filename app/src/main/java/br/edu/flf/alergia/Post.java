package br.edu.flf.alergia;

/**
 * Created by tiagosombra on 29/05/17.
 */
public class Post {

    private String postMarca;

    private String postProduto;

    private String postAtualizacao;

    private String postLeite;

    private String postOvo;

    private String postGluten;

    private String postSoja;



    public String getPostTitle() {
        return postMarca;
    }

    public void setPostTitle(String postTitle) {
        this.postMarca = postTitle;
    }

    public String getPostSubTitle() {
        return postProduto;
    }

    public void setPostSubTitle(String postSubTitle) {
        this.postProduto = postSubTitle;
    }

    public String getPostAtualizacao() {
        return postAtualizacao;
    }

    public void setPostAtualizacao(String postAtualizacao) {
        this.postAtualizacao = postAtualizacao;
    }

    public String getPostLeite() {
        return postLeite;
    }

    public void setPostLeite(String postLeite) {
        this.postLeite = postLeite;
    }

    public String getPostOvo() {
        return postOvo;
    }

    public void setPostOvo(String postOvo) {
        this.postOvo = postOvo;
    }

    public String getPostGluten() {
        return postGluten;
    }

    public void setPostGluten(String postGluten) {
        this.postGluten = postGluten;
    }

    public String getPostSoja() {
        return postSoja;
    }

    public void setPostSoja(String postSoja) {
        this.postSoja = postSoja;
    }

    public Post(String postMarca, String postProduto, String postAtualizacao, String postLeite, String postOvo, String postGluten, String postSoja) {
        this.postMarca = postMarca;
        this.postProduto = postProduto;
        this.postAtualizacao = postAtualizacao;
        this.postLeite = postLeite;
        this.postOvo = postOvo;
        this.postGluten = postGluten;
        this.postSoja = postSoja;
    }
}