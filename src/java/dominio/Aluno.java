package dominio;

public class Aluno {
    private int matricula;
    private String nome;
    private int idade;
    private String email;

    public Aluno() {}

    public Aluno(int matricula, String nome, int idade, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

