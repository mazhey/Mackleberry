

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculateAvgGrade
 */
@WebServlet("/CalculateAvgGrade")
public class CalculateAvgGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateAvgGrade() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    static Connection conn;
    private PreparedStatement preStatement;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String Assignment=request.getParameter("assignment");
		String Grade=request.getParameter("grade");
		out.println("<html><body>");
		String url = "jdbc:oracle:thin:system/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "TESTUSERDB");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, props);
			Statement stmt = conn.createStatement();
			
		
			
			ResultSet result2 = stmt.executeQuery("select Avg(Grade) as GradeAverage from STUDENT1" );
			out.println("<head>");
			out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
			out.println("<body>");
			out.println(" <div class=\"container\">");
			double Average;
            while (result2.next()) {
            	Average = result2.getDouble("GradeAverage");
				out.println("Student's average grade is" +" " +Average);
		
			}
            out.println("<br>");
            out.println("<a href =\"http://localhost:8080/MackleberryGradebook/AddGrades.html\">AddGrades</a><br>");
            
            out.println("</div>");
			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
			out.println("</body>");
			out.println("</html>");
			conn.close();

		} catch (Exception e) {

			String msg = e.getMessage();
			out.println("<p>" + msg);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
