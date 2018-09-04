package ve.com.phl.remesafacil.Requests;

/**
 * Created by ghoss on 02/09/2018.
 */
public class RequestPresenter implements RequestContract.Presenter {
    private RequestContract.View mView;

    public RequestPresenter(RequestContract.View mView) {
        this.mView = mView;
    }



    @Override
    public String convertToPcl(String bsS, float tasa, boolean conver) {
        String pcl="";
        if (!bsS.isEmpty()){


            try{
                float bs = Float.valueOf(bsS);
                float pc = 0;
                if (!conver)
                pc = bs/tasa;
                else
                    pc = bs*tasa;
                pcl = String.valueOf(pc);
            }
            catch (NumberFormatException ignored){

            }
        }
        return pcl;
    }


    @Override
    public float setValue(String tasa) {
        if (tasa.length()==0) {
            return 0;
        }
        try{
            return Float.valueOf(tasa);
        }
        catch (NumberFormatException ignored){
            return 0;
        }
    }

    @Override
    public void validData(String account, String name, String id, String observations, String bss, String pcl) {
        boolean value=true;
        if (bss.isEmpty()) {
            mView.showBsEmptyError();
            value = false;
        }
        else
        if (pcl.isEmpty()){
            mView.showPcEmptyError();
            value = false;
        }
        else
        if (account.isEmpty()){
            mView.showAccountError();
            value = false;
        }
        else
        if(account.length()<20) {
            mView.showAccountInvalidError();
            value = false;
        }
        else
        if(name.isEmpty()) {
            mView.showNameError();
            value = false;
        }
        else
        if (id.isEmpty()){
            mView.showIdError();
            value = false;
        }
        else
        if (observations.length()<5)
        {
            mView.showObservationError();
            value = false;
        }

        if (value)
            mView.sendRequest(account,name,id,observations,bss,pcl);
    }

    @Override
    public boolean chooseConvert(boolean conver) {
        conver = !conver;

        if (conver)
            mView.PcltoBsS();
        else
            mView.bsStoPcl();

        return conver;
    }


    @Override
    public void bindView(RequestContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbindView() {
        this.mView = null;
    }
}
