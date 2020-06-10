package Model;

public class SizeFigure {
     public int XFigure;
     public int YFigure;

   public int sizeXtl,sizeYtl,sizeXbr,sizeYbr;

   public SizeFigure(int sizeXtl,int sizeYtl,int sizeXbr,int sizeYbr){
       this.sizeXbr =sizeXbr;
       this.sizeXtl =sizeYtl;
       this.sizeYtl =sizeYtl;
       this.sizeYbr =sizeYbr;
   }

   public void CalculateSize(){
       this.XFigure = sizeXtl - sizeXbr;
       this.YFigure = sizeYtl - sizeYbr;
   }
}
