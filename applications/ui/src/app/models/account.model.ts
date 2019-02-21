export interface Account {
    id: string;
    status: string;
    currency: string;
    buying_power: string;
    cash: number;
    cash_withdrawable: number;
    portfolio_value: number;
    pattern_day_trader: boolean;
    trading_blocked: boolean;
    transfers_blocked: boolean;
    account_blocked: boolean;
    created_at: string    
}