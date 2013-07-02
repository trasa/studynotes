
namespace ConnectFour.Game
{
    /// <summary>
    /// A Match is an instance of the ConnectFour game, between two 
    /// players, Red and Black.  The Match keeps track of the board
    /// state, who's turn it is, and determines when the game is 
    /// over.
    /// </summary>
    public class Match
    {
        public Match()
        {
            Container = new Container();
            // Black goes first. (Smoke before Fire...)
            CurrentPlayer = PieceColor.Black;
            Winner = PieceColor.None;
        }

        public Container Container { get; private set; }

        public PieceColor CurrentPlayer { get; private set; }

        public PieceColor Winner { get; private set; }

        public bool GameOver { get; private set; }

        /// <summary>
        /// The Current Player sets a piece into the game board.
        /// 
        /// This swaps the Current Player and checks to see if the
        /// game is over or not.
        /// </summary>
        /// <param name="column">The column the piece goes into.</param>
        public void AddPiece(int column)
        {
            if (GameOver)
            {
                throw new IllegalPlacementException("The game is over, no more placements allowed.");
            }
            if (Container.AddPiece(CurrentPlayer, column))
            {
                // CurrentPlayer wins!
                Winner = CurrentPlayer;
                GameOver = true;
            } 
            else if (Container.IsContainerFull)
            {
                // it's a tie
                Winner = PieceColor.None;
                GameOver = true;
            }
            else
            {
                // swap the current player
                CurrentPlayer = CurrentPlayer == PieceColor.Red ? PieceColor.Black : PieceColor.Red;
            }
        }
    }
}
