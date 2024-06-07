package spaceplus.jsp;

import spaceplus.http.HttpRequest;
import spaceplus.http.HttpResponse;
import spaceplus.pages.RequestDispatcher;

public class Login implements RequestDispatcher {
    @Override 
    public void forward(HttpRequest request, HttpResponse response) {
        try {
           response.sendResponseHeaders(200, 0);
           response.setContentType("text/html");
           var out = response.getResponseBody();
out.write("<!DOCTYPE html>".getBytes());
out.write("<html lang=\"pt-BR\">".getBytes());
out.write("<head>".getBytes());
out.write("    <meta charset=\"UTF-8\">".getBytes());
out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">".getBytes());
out.write("    <title>Login</title>".getBytes());
out.write("    <link rel=\"stylesheet\" href=\"login/style.css\">    ".getBytes());
out.write("</head>".getBytes());
out.write("<body>".getBytes());
out.write("    <div class=\"container\">".getBytes());
out.write("        <p id=\"errorlabel\" class=\"title-header\" style=\"visibility: hidden; color: red;\">Usuário e/ou senha inválidos</p>".getBytes());
out.write("        <p id=\"header\" class=\"title-header\" style=\"visibility: hidden;\">Preencha todos os campos</p>".getBytes());
out.write("        <div id=\"subcontainer\" class=\"subcontainer\">".getBytes());
out.write("            <input class=\"flow-input\" type=\"text\" placeholder=\"Usuário\" autocomplete=\"on\" spellcheck=\"false\" title=\"Digite seu usuário\" autofocus required id=\"username\" value=\"\" autocapitalize=\"none\" autocorrect=\"off\">".getBytes());
out.write("            <input class=\"flow-input\" type=\"password\" id=\"password\" placeholder=\"Senha\" autocomplete=\"current-password\" spellcheck=\"false\" title=\"Digite sua senha\" value=\"\" autocapitalize=\"no\" autocorrect=\"off\" required autofocus>".getBytes());
out.write("            <p class=\"err-msg\" style=\"display: none;\">Preencha todos os campos</p>".getBytes());
out.write("            <p class=\"text-registrar\">Não tem uma conta?".getBytes());
out.write("                <strong class=\"link-registrar\">".getBytes());
out.write("                    <a href=\"/spaceplus/signup\">Registre-se</a>".getBytes());
out.write("                </strong>".getBytes());
out.write("            </p>".getBytes());
out.write("            <button class=\"commitForm disabled\" id=\"commit\">Entrar</button>".getBytes());
out.write("        </div>".getBytes());
out.write("    </div>".getBytes());
out.write("    <script src=\"login/script.js\"></script>".getBytes());
out.write("</body>".getBytes());
out.write("</html>".getBytes());
           response.getResponseBody().close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}