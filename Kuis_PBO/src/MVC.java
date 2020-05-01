public class MVC {
    View view = new View();
    Model model = new Model();
    DAO dao = new DAO();
    Controller control = new Controller(model, view, dao);
}