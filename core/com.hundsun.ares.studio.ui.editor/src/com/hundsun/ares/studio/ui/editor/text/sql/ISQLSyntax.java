package com.hundsun.ares.studio.ui.editor.text.sql;

/**
 * SQL Syntax words.
 * 
 * @author Hui Cao
 */
public interface ISQLSyntax
{

    /**
     * Gets an array of SQL built-in function names.
     */
    public String[] getFunctions();

    /**
     * Gets an array of SQL predicates.
     */
    public String[] getPredicates();

    /**
     * Gets an array of SQL reserved keywords.
     */
    public String[] getReservedwords();

    /**
     * Gets an array of SQL unreserved keywords.
     */
    public String[] getUnreservedwords();

    /**
     * Gets an array of SQL datatype names.
     */
    public String[] getTypes();

    /**
     * Gets an array of SQL constants.
     */
    public String[] getConstants();

    /**
	 * Gets an array of arrays containing all SQL words, including keywords,
	 * constants, predicates, data types names, and function names.
	 */
    public Object[] getAllWords();

    /**
     * Gets an array of SQL single line comments.
     */
    public String[] getSingleLineComments();
    
    /**
     * Gets an array of global variables supported by this database.
     */
    public String[] getGlobalVariables();
}
