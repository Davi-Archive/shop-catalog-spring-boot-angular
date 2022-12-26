import Image from "next/image";
import React from "react";
import MainLogo from "../../public/images/main_image.svg";
import { Navbar, ButtonIcon } from "../../shared/components";

const HomePage = () => {
  return (
    <>
      <Navbar />
      <main className="home-container">
        <div className="home-card">
          <div className="home-content-container">
            <h1>Conheça o melhor catálogo de produtos</h1>
            <p>
              Ajudaremos você a encontrar os melhores produtos disponíveis no
              mercado.
            </p>
            <ButtonIcon text="INICIE AGORA A SUA BUSCA" />
          </div>
          <div className="home-image-container">
            <Image
              className="home-logo-image"
              src={MainLogo}
              alt="Logo principal"
            />
          </div>
        </div>
      </main>
    </>
  );
};

export default HomePage;
