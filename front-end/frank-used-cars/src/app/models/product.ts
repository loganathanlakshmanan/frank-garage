export class Product {
  id: number;
  manufacturer_name: string;
  model_name: string;
  model_year: number;
  price: number;
  licensed: boolean;
  date_added: string;
  warehouse_id: string;

  constructor(id: number,
              manufacturer_name: string,
              model_name: string,
              model_year: number,
              price: number,
              licensed: boolean,
              date_added: string,
              warehouse_id: string)
  {
    this.id = id;
    this.manufacturer_name = manufacturer_name;
    this.model_name = model_name;
    this.model_year = model_year;
    this.price = price;
    this.licensed = licensed;
    this.date_added = date_added;
    this.warehouse_id = warehouse_id;
  }
}
