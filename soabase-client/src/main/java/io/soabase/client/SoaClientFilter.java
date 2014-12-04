package io.soabase.client;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class SoaClientFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // NOP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        if ( request instanceof HttpServletRequest )
        {
            final HttpServletRequest httpRequest = (HttpServletRequest)request;
            String id = httpRequest.getHeader(SoaRequestId.REQUEST_ID_HEADER_NAME);
            if ( id != null )
            {
                SoaRequestId.set(id);
            }
            else
            {
                final String newId = SoaRequestId.create();
                final List<String> headerNames = Collections.list(httpRequest.getHeaderNames());
                headerNames.add(SoaRequestId.REQUEST_ID_HEADER_NAME);

                request = new HttpServletRequestWrapper(httpRequest)
                {
                    @Override
                    public String getHeader(String name)
                    {
                        if ( name.equals(SoaRequestId.REQUEST_ID_HEADER_NAME) )
                        {
                            return newId;
                        }
                        return super.getHeader(name);
                    }

                    @Override
                    public Enumeration<String> getHeaders(String name)
                    {
                        if ( name.equals(SoaRequestId.REQUEST_ID_HEADER_NAME) )
                        {
                            return Collections.enumeration(Arrays.asList(newId));
                        }
                        return super.getHeaders(name);
                    }

                    @Override
                    public Enumeration<String> getHeaderNames()
                    {
                        return Collections.enumeration(headerNames);
                    }
                };
            }
            try
            {
                chain.doFilter(request, response);
            }
            finally
            {
                SoaRequestId.clear();
            }
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy()
    {
        // NOP
    }
}
