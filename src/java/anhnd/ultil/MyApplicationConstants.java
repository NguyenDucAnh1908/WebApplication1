/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.ultil;

/**
 *
 * @author DucAnh
 */
public class MyApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String LOGIN_CONTROLLER = "loginController";
    }

    public class LoginFeature {

        public static final String INVALID_PACE = "invalidPage";
        public static final String SEARCH_PAGE = "searchPage";
    }
    public class logoutFeature{
        public static final String LOGIN_PAGE = "loginPage";
        public static final String ERROR_PAGE = "errorPage";
    }

    public class searchLastnameFeature {

        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastNameController";
        public static final String SEARCH_PAGE = "searchPage";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class deleteAccountFeature {

        public static final String ERROR_PAGE = "errorPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastNameController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";

    }

    public class updateAccountFeature {
        
        public static final String ERROR_PAGE = "errorPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastNameController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
    }
    
    public class ListProductFeature{
        public static final String LOGIN_PAGE ="loginPage";
        public static final String SHOPPING_PAGE="shoppingPage";
        
    }
    
    public class AddProductToCartFeature{
        public static final String ERROR_PAGE = "errorPage";
        public static final String LIST_PRODUCT_CONTROLLER = "listProductController";
    }
    
    public class ViewCartFeature{
        public static final String VIEW_CART_PAGE ="viewCartPage";
    }
    
    public class RemoveProductFeature{
        public static final String VIEW_CART_PAGE ="viewCartPage";
        public static final String REMOVE_BOOK_FROM_CART = "removeBookFromCartAction";
    }
    
    public class ConfirmProductCheckOutFeature{
        public static final String VIEW_CART_PAGE ="viewCartPage";
        public static final String CONFIRM_CHECK_OUT_PAGE = "confirmCheckOutPage";
        public static final String CHECK_OUT_ORDER_CONTROLLER = "checkOutAction";
        public static final String CONFIRM_CHECK_OUT_CONTROLLER = "confirmCheckOutAction";
        
        
    }
     public class CheckOutProductFeatures {

        public static final String CONFIRM_CHECK_OUT_PAGE = "confirmCheckOutPage";
        public static final String CHECK_OUT_SUCCESS_PAGE = "checkOutSuccessPage";
    }
     public class CartProductFeature{
         public static final String ERROR_PAGE = "errorPage";
        public static final String REMOVE_BOOK_FROM_CART = "removeBookFromCartAction";
        public static final String CONFIRM_CHECK_OUT_CONTROLLER = "confirmCheckOutAction";
     }
     
     

}
