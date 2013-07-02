using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

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
        private readonly Container container = new Container();

        // TODO remove me
        public Container Container { get { return container; } }

        public Match()
        {
            // Black goes first. (Smoke before Fire...)
            CurrentPlayer = PieceColor.Black;
            Winner = PieceColor.None;
        }

        public PieceColor CurrentPlayer { get; private set; }

        public PieceColor Winner { get; private set; }

        public bool GameOver { get; private set; }

        public void AddPiece(int column)
        {
            if (GameOver)
            {
                throw new IllegalPlacementException("The game is over, no more placements allowed.");
            }
            if (container.AddPiece(CurrentPlayer, column))
            {
                // CurrentPlayer wins!
                Winner = CurrentPlayer;
                GameOver = true;
            } 
            else if (container.IsContainerFull)
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
