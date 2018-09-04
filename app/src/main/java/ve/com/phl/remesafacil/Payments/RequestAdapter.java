package ve.com.phl.remesafacil.Payments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ve.com.phl.remesafacil.Model.Request;
import ve.com.phl.remesafacil.R;

import static ve.com.phl.remesafacil.Model.Request.ST_CANCELADA;
import static ve.com.phl.remesafacil.Model.Request.ST_COMPLETO;
import static ve.com.phl.remesafacil.Model.Request.ST_EN_ESPERA_DE_TRANSFERENCIA;
import static ve.com.phl.remesafacil.Model.Request.ST_EN_PROCESO;
import static ve.com.phl.remesafacil.Model.Request.ST_INCOMPLETA;
import static ve.com.phl.remesafacil.Model.Request.ST_RECHAZADA;
import static ve.com.phl.remesafacil.Model.Request.ST_SOLICITUD_ENVIADA;
import static ve.com.phl.remesafacil.Model.Request.ST_TRANSFERENCIA_RECIBIDA;

/**
 * Created by ghoss on 03/09/2018.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private ArrayList<Request> mRequests;
    private Context context;
    private OnClick onClick;

    public RequestAdapter(ArrayList<Request> mRequests, Context context, OnClick onClick) {
        this.mRequests = mRequests;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_row_layout, parent, false);
        return new RequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RequestViewHolder holder, int position) {
        final Request request = mRequests.get(position);
        if (request!=null)
        {
            String solicitud = "Deseo Recibir la cantidad de bolívares:"+
                request.getBs()+" Bs.S"+"\r\n"+
                "\r\nEn la cuenta: "+ request.getAccountNumber()+"\r\n"+
                "\r\nA nombre de: "+request.getAccountName() +"\r\n"+
                "\r\nCedula o Rif: "+request.getAccountId()+"\r\n"+
                "\r\nObservaciones: "+request.getObservations()+"\r\n"+
                "\r\nPor los cuales voy a depositar: "+request.getPc()+" PCL";
            holder.mTvRequest.setText(solicitud);
            String date = request.getDate();
            date = date.substring(7);
            holder.mTvDate.setText(date);
            holder.mTvStatus.setText(getStatus(request.getStatus()));
            if (request.getResponse()!=null &&!request.getResponse().isEmpty()) {
                holder.mLlResponse.setVisibility(View.VISIBLE);
                holder.mTvAnswer.setText(request.getResponse());
            }
            if (request.getPhotoBs()!=null &&!request.getPhotoBs().isEmpty())
                holder.mLlComprobante.setVisibility(View.VISIBLE);

            holder.mLlTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mLlView.getVisibility()==View.GONE)
                        holder.mLlView.setVisibility(View.VISIBLE);
                    else
                        holder.mLlView.setVisibility(View.GONE);
                }
            });


            holder.mBtCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onDeleteRequest(request);
                }
            });

        }

    }

    private String getStatus(int status) {
        switch (status) {
            case ST_SOLICITUD_ENVIADA:
                return "Solicitud Enviada";
            case ST_EN_ESPERA_DE_TRANSFERENCIA:
                return "Esperando Depósito";
            case ST_TRANSFERENCIA_RECIBIDA:
                return "Depósito Recibido";
            case ST_EN_PROCESO:
                return "En Proceso";
            case ST_COMPLETO:
                return "Completa";
            case ST_RECHAZADA:
                return "Rechazada";
            case ST_INCOMPLETA:
                return "Incompleta";
            case ST_CANCELADA:
                return "Cancelada";
            default:
                return "";
        }

    }

    @Override
    public int getItemCount() {
        return  mRequests.size();
    }

    public interface OnClick {
        void onDeleteRequest(Request request);
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date) TextView mTvDate;
        @BindView(R.id.tv_status) TextView mTvStatus;
        @BindView(R.id.tv_request) TextView mTvRequest;
        @BindView(R.id.tv_answer) TextView mTvAnswer;
        @BindView(R.id.ll_comprobante) LinearLayout mLlComprobante;
        @BindView(R.id.ll_view) LinearLayout mLlView;
        @BindView(R.id.ll_response) LinearLayout mLlResponse;
        @BindView(R.id.ll_title) LinearLayout mLlTitle;
        @BindView(R.id.bt_cancel) Button mBtCancel;
        @BindView(R.id.bt_send) ImageButton mBtSend;
        @BindView(R.id.bt_show) ImageButton mBtShow;

        RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public ArrayList<Request> getRequests() {
        return mRequests;
    }

    public void setRequests(ArrayList<Request> mRequests) {
        this.mRequests = mRequests;
    }
}
