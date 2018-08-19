package by.epam.rentacar.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.Writer;

/**
 * Defines tag for the pagination.
 */
public class Paginator extends SimpleTagSupport {

    /**
     * Constant variables to display pagination.
     */
    private static final String NEXT_PAGE = "&raquo";
    private static final String PREVIOUS_PAGE = "&laquo";
    private static final String PAGINATION_START_TAG = "<ul class = \"pagination pagination-sm justify-content-center\">";
    private static final String PAGINATION_END_TAG = "</ul>";
    private static final String PAGINATION_ITEM_START_TAG = "<li class=\"page-item";
    private static final String PAGINATION_ITEM_END_TAG = "</li>";
    private static final String PAGE_LINK_START_TAG = "<a class=\"page-link\" href=\"";
    private static final String PAGE_LINK_END_TAG = "</a>";
    private static final String ACTIVE_CLASS = "active";
    private static final String START_OF_TAG = "<";
    private static final String END_OF_TAG = ">";
    private static final String QUOTE = "\"";
    private static final String WHITESPACE = " ";

    /**
     * The url of the page to go after clicking.
     */
    private String url;

    /**
     * The index of the current page.
     */
    private int currentPage;

    /**
     * The number of all pages.
     */
    private int totalPages;

    /**
     * The max number of page links to choose.
     */
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

            out.write(PAGINATION_START_TAG);

            if (currentPage > 1) {
                out.write(makeLink(currentPage - 1, PREVIOUS_PAGE, false));
            }

            for (int i = pgStart; i < pgEnd; i++) {
                if (i == currentPage) {
                    out.write(makeLink(i, true));
                } else {
                    out.write(makeLink(i));
                }
            }

            if (!isLastPage) {
                out.write(makeLink(currentPage + 1, NEXT_PAGE, false));
            }

            out.write(PAGINATION_END_TAG);

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
        StringBuilder builder = new StringBuilder(PAGINATION_ITEM_START_TAG);
        if (isActive) {
            builder.append(WHITESPACE).append(ACTIVE_CLASS);
        }
        builder.append(QUOTE).append(END_OF_TAG)
                .append(PAGE_LINK_START_TAG)
                .append(url.replace("##", String.valueOf(page)))
                .append(QUOTE).append(END_OF_TAG)
                .append(text)
                .append(PAGE_LINK_END_TAG)
                .append(PAGINATION_ITEM_END_TAG);

        return builder.toString();
    }
}
