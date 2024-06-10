export interface AppUserDTO {
    username: string;
    password: string | null;
    balance: number | null;
    favouriteGenres: string[] | null;
    playtime: number | null;
    timezone: string | null;
    subscriptionType: string | null;
    games: GameDTO[] | null;
    wishlist: GameDTO[] | null;
    friends: FriendDTO[] | null;
}

export interface FriendDTO extends AppUserDTO {
    // mostPlayedGenres: string[] | null;
    // timesPlayedTogether: number | null;
    // timeSinceLastGame: number | null;
    // numSharedGames: number | null;
    // timeSinceLastActivity: number | null;
}

export interface GameDTO {
    name: string;
    genre: string;
    price: number;
    releaseDate: string;
    singlePlayer: boolean;
    onSale: number;
    rating: number;
    beta: boolean;
    betaReleaseDate: string;
    saleEndDate: string;
}

export interface NotificationDTO {
    name: string;
    description: string;
    user: AppUserDTO;
}

export interface RecommendationDTO {
    type: string;
    description: string;
    user: AppUserDTO;
    game: GameDTO;
}

export interface SessionDTO {
    beginTime: string;
    duration: number;
    game: GameDTO;
    friends: AppUserDTO[];
}

export interface GameScoreDTO {
    score: number;
    user: AppUserDTO;
    game: GameDTO;
}

export interface FriendScoreDTO {
    // score: number;
    user: AppUserDTO;
    friendRecommendation: AppUserDTO;
}

export interface LoginDTO {
    username: string;
    password: string;
}