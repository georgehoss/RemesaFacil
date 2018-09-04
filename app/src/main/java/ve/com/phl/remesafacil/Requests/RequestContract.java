package ve.com.phl.remesafacil.Requests;

import ve.com.phl.remesafacil.BasePresenter;
import ve.com.phl.remesafacil.BaseView;

/**
 * Created by ghoss on 02/09/2018.
 */
public interface RequestContract {
    interface View extends BaseView<Presenter> {
        void setBss(String bss);
        void setPcl(String pcl);
        void sendRequest(String account,String name,String id,String observations,String bss, String pcl);
        void showConfirmDialog();
        void getTasa();
        void setTasa(String tasa);
        void setDate(String date);
        void showAccountError();
        void showAccountInvalidError();
        void showNameError();
        void showIdError();
        void showObservationError();
        void setInputBs();
        void showBsEmptyError();
        void showPcEmptyError();
        void bsStoPcl();
        void PcltoBsS();
        void getUserInfo();
        void showSuccessfullMsg();
        void showUnsuccessfullMsg();
    }
    interface Presenter extends BasePresenter<View> {
        String convertToPcl(String bsS, float tasa,boolean conver);
        float setValue (String tasa);
        void validData(String account,String name,String id,String observations,String bss, String pcl);
        boolean chooseConvert(boolean conver);
    }
}
