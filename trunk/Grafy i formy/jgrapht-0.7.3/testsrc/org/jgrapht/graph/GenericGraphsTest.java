/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* --------------------------
 * GenericGraphsTest.java
 * --------------------------
 * (C) Copyright 2006-2007, by HartmutBenz and Contributors.
 *
 * Original Author:  Hartmut Benz
 * Contributor(s):   John V. Sichi
 *
 * $Id: GenericGraphsTest.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 * ??-???-2006 : Initial revision (HB);
 *
 */
package org.jgrapht.graph;

import org.jgrapht.*;


/**
 * A unit test for graph generic vertex/edge parameters.
 *
 * @author Hartmut Benz
 */
public class GenericGraphsTest
    extends EnhancedTestCase
{
    //~ Instance fields --------------------------------------------------------

    Graph<Object, ? extends DefaultEdge> objectGraph;
    Graph<FooVertex, FooEdge> fooFooGraph;
    Graph<BarVertex, BarEdge> barBarGraph;
    Graph<FooVertex, BarEdge> fooBarGraph;

    //~ Constructors -----------------------------------------------------------

    /**
     * @see junit.framework.TestCase#TestCase(java.lang.String)
     */
    public GenericGraphsTest(String name)
    {
        super(name);
    }

    //~ Methods ----------------------------------------------------------------

    // ~ Methods ---------------------------------------------------------------

    public void testLegalInsertStringGraph()
    {
        String v1 = "Vertex1";
        Object v2 = "Vertex2";
        objectGraph.addVertex(v1);
        objectGraph.addVertex(v2);
        objectGraph.addEdge(v1, v2);
    }

    public void testLegalInsertFooGraph()
    {
        FooVertex v1 = new FooVertex();
        FooVertex v2 = new FooVertex();
        BarVertex vb1 = new BarVertex();
        BarVertex vb2 = new BarVertex();
        fooFooGraph.addVertex(v1);
        fooFooGraph.addVertex(v2);
        fooFooGraph.addVertex(vb1);
        fooFooGraph.addVertex(vb2);
        fooFooGraph.addEdge(v1, v2);
        fooFooGraph.addEdge(vb1, vb2);
        fooFooGraph.addEdge(v1, vb2);
        fooFooGraph.addEdge(v1, v2, new BarEdge());
        fooFooGraph.addEdge(v1, vb2, new BarEdge());
        fooFooGraph.addEdge(vb1, vb2, new BarEdge());
    }

    public void testLegalInsertBarGraph()
    {
        BarVertex v1 = new BarVertex();
        BarVertex v2 = new BarVertex();
        barBarGraph.addVertex(v1);
        barBarGraph.addVertex(v2);
        barBarGraph.addEdge(v1, v2);
    }

    public void testLegalInsertFooBarGraph()
    {
        FooVertex v1 = new FooVertex();
        FooVertex v2 = new FooVertex();
        BarVertex vb1 = new BarVertex();
        BarVertex vb2 = new BarVertex();
        fooFooGraph.addVertex(v1);
        fooFooGraph.addVertex(v2);
        fooFooGraph.addVertex(vb1);
        fooFooGraph.addVertex(vb2);
        fooFooGraph.addEdge(v1, v2);
        fooFooGraph.addEdge(vb1, vb2);
        fooFooGraph.addEdge(v1, vb2);
    }

    public void testAlissaHacker()
    {
        DirectedGraph<String, CustomEdge> g =
            new DefaultDirectedGraph<String, CustomEdge>(CustomEdge.class);
        g.addVertex("a");
        g.addVertex("b");
        g.addEdge("a", "b");
        CustomEdge custom = g.getEdge("a", "b");
        String s = custom.toString();
        assertEquals("Alissa P. Hacker", s);
    }

    /**
     * .
     */
    protected void setUp()
    {
        objectGraph =
            new DefaultDirectedGraph<Object, DefaultEdge>(
                DefaultEdge.class);
        fooFooGraph = new SimpleGraph<FooVertex, FooEdge>(FooEdge.class);
        barBarGraph = new SimpleGraph<BarVertex, BarEdge>(BarEdge.class);
    }

    //~ Inner Classes ----------------------------------------------------------

    public static class CustomEdge
        extends DefaultEdge
    {
        private static final long serialVersionUID = 1L;

        public String toString()
        {
            return "Alissa P. Hacker";
        }
    }

    public static class FooEdge
        extends DefaultEdge
    {
        private static final long serialVersionUID = 1L;
    }

    private class FooVertex
    {
        String str;

        public FooVertex()
        {
            super();
            str = "empty foo";
        }

        public FooVertex(String s)
        {
            str = s;
        }
    }

    public static class BarEdge
        extends FooEdge
    {
        private static final long serialVersionUID = 1L;
    }

    private class BarVertex
        extends FooVertex
    {
        public BarVertex()
        {
            super("empty bar");
        }

        public BarVertex(String s)
        {
            super(s);
        }
    }
}

// End GenericGraphsTest.java
