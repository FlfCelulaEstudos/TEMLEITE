package br.edu.flf.alergia;

/**
 * Created by tiagosombra on 29/05/17.
 */
public class Post {

    private String postMarca;

    private String postProduto;

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

    public Post(String postTitle, String postSubTitle) {
        this.postMarca = postTitle;
        this.postProduto = postSubTitle;
    }
}