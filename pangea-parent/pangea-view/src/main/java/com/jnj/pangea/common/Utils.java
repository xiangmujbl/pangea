package com.jnj.pangea.common;

import com.alibaba.fastjson.JSONObject;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.IBiz;
import com.jnj.adf.client.api.IRemoteService;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.support.IGridService;
import com.jnj.adf.grid.utils.DateUtil;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.utils.Util;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    //Names
    public static final String PANGEA = "Pangea";
    public static final String OMP = "omp";
    public static final String LATAM_ROOT = "LATAM_ROOT";
    public static final String LATAM_SKU = "LATAM_SKU";
    public static final String LATAM_TECH = "LATAM_TECH";
    public static final String PROJECT_ONE = "project_one";
    public static final String PROJECT_ONE_DEV = "Project_One";
    public static final String CONS_LATAM = "CONS_LATAM";
    public static final String LA = "LA";
    public static final String EMS = "[EMS]";
    public static final String USD = "USD";
    public static final String NGEMS = "NGEMS";

    //Letters
    public static final String O = "O";
    public static final String A = "A";
    public static final String V = "V";
    public static final String C = "C";
    public static final String S = "S";
    public static final String M = "M";
    public static final String N = "N";
    public static final String E = "E";
    public static final String I = "I";
    public static final String J = "J";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String F = "F";
    public static final String L = "L";
    public static final String K = "K";

    //Keywords
    public static final String NP = "NP";
    public static final String YES = "YES";
    public static final String NO = "NO";
    public static final String WE = "WE";
    public static final String ACTIVE = "ACTIVE";
    public static final String FALSE = "FALSE";
    public static final String DEFAULT = "DEFAULT";
    public static final String INCLUSION = "I";
    public static final String EXCLUSION = "E";
    public static final String WHERE = "WHERE";
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String ALL = "ALL";

    //Separators / Operators
    public static final String UNDERLINE = "_";
    public static final String LINE = "-";
    public static final String BACK_SLANT = "/";
    public static final String COLON = ":";
    public static final String STAR = "*";
    public static final String OPERATOR_EQUAL = "=";
    public static final String OPERATOR_LESS_THAN = "<";
    public static final String OPERATOR_LESS_THAN_EQUAL = "<=";
    public static final String OPERATOR_GREATER_THAN = ">";
    public static final String OPERATOR_GREATER_THAN_EQUAL = ">=";
    public static final String OPERATOR_NOT_EQUAL = "!=";
    public static final String BLANK = "";
    public static final String SPACE = " ";

    //Date Time
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy/MM/dd";
    public static final String YYYYMMDD_ZERO = "00000000";
    public static final String YYYY_MM_DD_ZERO = "0000/00/00";
    public static final String HH_NN_SS_ZERO = " 00:00:00";
    public static final String YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYYDDMMHHMMSS = "yyyy/DD/mm HH:mm:ss";
    public static final String YYYYMMDD_WITH_DASH = "yyyy-MM-dd";
    public static final String MMDYYYY = "MM/d/yyyy";
    public static final String DDMMYYYY = "dd/MM/yyyy";

    //Numbers
    public static final String ZERO = "0";
    public static final String ZEROZERO = "0.0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String SEVEN = "7";
    public static final String THIRTY = "30";
    public static final String THIRTY_ONE = "31";

    //Regex
    public static final String PATTERN_DIGITAL = "^-?[1-9]\\d*$";




}
