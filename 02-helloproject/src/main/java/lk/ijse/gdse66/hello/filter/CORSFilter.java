package lk.ijse.gdse66.hello.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CORSFilter " , urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String origin = req.getHeader("origin");

        if(origin == null){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST,"CORS Policy Violation");
            return;
        }

        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-control-Allow-Headers", "Content-type");
        res.addHeader("Access-control-Allow-Methods", "DELETE,PUT");

        chain.doFilter(req,res);

    }


}
