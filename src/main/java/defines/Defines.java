package defines;

public class Defines {

    public class AmazonExcelValues{

        public final static int ITEM_SKU = 0;
        public final static int EAN = 1;
        public final static int BARCODE_TYPE = 2;
        public final static int ITEM_NAME = 3;
        public final static int BRAND_NAME = 4;
        public final static int MANUFACTURER_NAME= 5;
        public final static int DESCRIPTION= 6;
        public final static int PRODUCT_TYPE= 7;
        public final static int PART_NUMBER= 8;
        public final static int MODEL = 9;
        public final static int UPDATE_DELETE= 10;

        public final static int PRICE= 11;
        public final static int QUANTITY= 12;
        public final static int CONDITION= 13;
        public final static int SHIPPING_GROUP= 24;

        public final static int BULLET_POINT_1= 36;
        public final static int BULLET_POINT_2= 37;
        public final static int BULLET_POINT_3= 38;
        public final static int BULLET_POINT_4= 39;
        public final static int BULLET_POINT_5= 40;

        public final static int BROWSE_NODE=41;
        public final static int SEARCH_KEYWORDS=42;

        public final static int MAIN_IMAGE=43;
        public final static int IMAGE1=44;
        public final static int IMAGE2=45;
        public final static int IMAGE3=46;
        public final static int IMAGE4=47;
        public final static int IMAGE5=48;
        public final static int IMAGE6=49;
        public final static int IMAGE7=50;
        public final static int IMAGE8=51;

        public final static int PARENT_CHILD=59;
        public final static int PARENT__SKU=60;
        public final static int RELATION_TYPE=61;
        public final static int VARIATION=62;

        public final static int COLOR=67;
        public final static int SIZE=68;


    }
    public class DBProperties{
        public final static String USERNAME = "db.user";
        public final static String PASSWORD = "db.password";
        public final static String URL = "db.url";
        public final static String DATABASE = "db.database";


    }

    public class GeneralInformation{

        public final static String BRAND = "Handy Lux";
        public final static String MANUFACTURER = "Handy Lux";
        public final static String UPDATE_DELETE= "Aktualisierung";
        public final static String CHILD = "Child";
        public final static String PARENT = "Parent";
        public final static String BARCODE_TYPE= "EAN";
        public final static String PRODUCT_TYPE= "PhoneAccessory";
        public final static int QUANTITY= 1;
        public final static String CONDITION= "NEU";
        public final static String RELATION_TYPE = "Variation";
    }

    public class GeneralInformationParser{
        public final static int ITEM_NAME = 0;
        public final static int DESCRIPTION = 1;
        public final static int BROWSE_NODE = 2;
        public final static int GENERIC_KEYWORDS = 3;
        public final static int VARIATION_THEME = 4;
    }

    public class VariationThemes {
        public final static String SIZE = "SizeName";
        public final static String COLOR = "ColorName";
        public final static String SIZECOLOR = "SizeName-ColorName";
    }
}
