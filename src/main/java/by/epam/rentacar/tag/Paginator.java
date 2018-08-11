package by.epam.rentacar.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.Writer;

public class Paginator extends SimpleTagSupport {

    private String url;
    private int currentPage;
    private int totalPages;
    private int maxLinks;

    @Override
    public void doTag() throws JspException, IOException {

        if (totalPages <= 1) {
            return;
        }

        Writer out = getJspContext().getOut();

        boolean isLastPage = currentPage == totalPages;
        int pgStart = Math.max(currentPage - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;

        if(pgEnd > totalPages + 1) {

            int diff = pgEnd - totalPages;
            pgStart -= (diff - 1);
            pgStart = (pgStart < 1) ? 1 : pgStart;
            pgEnd = totalPages + 1;

        }

        try {

            out.write("<ul class = \"pagination pagination-sm justify-content-center\">");

            if (currentPage > 1) {
                out.write(makeLink(currentPage - 1, "Previous", false));
            }

            for (int i = pgStart; i < pgEnd; i++) {
                if (i == currentPage) {
                    out.write(makeLink(i, true));
                } else {
                    out.write(makeLink(i));
                }
            }

            if (!isLastPage) {
                out.write(makeLink(currentPage + 1, "Next", false));
            }

            out.write("</ul>");

        } catch (IOException e) {
            throw new JspException("Error while making pagination", e);
        }



    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }

    private String makeLink(int page) {
        return makeLink(page, false);
    }

    private String makeLink(int page, boolean isActive) {
        return makeLink(page, String.valueOf(page), isActive);
    }

    private String makeLink(int page, String text, boolean isActive) {
        StringBuilder builder = new StringBuilder("<li class=\"page-item");
        if (isActive) {
            builder.append(" active");
        }
        builder.append("\">");
        builder.append("<a class=\"page-link\" href=\"")
                .append(url.replace("##", String.valueOf(page)))
                .append("\">")
                .append(text)
                .append("</a>");
        builder.append("</li>");

        return builder.toString();
    }
}
